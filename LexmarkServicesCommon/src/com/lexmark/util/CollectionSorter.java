package com.lexmark.util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.domain.Pagination;



public class CollectionSorter
{
	private static final Logger logger = LogManager.getLogger(CollectionSorter.class);
    public static final String SORT_ASCENDING = "ASCENDING";
    public static final String SORT_ASC = "ASC";
    public static final String SORT_DESCENDING = "DESCENDING";
    public static final String SORT_DES = "DES";

    public List sort(List collection)
    {
        return sort(collection, (List)null);
    }

    public List sort(List collection, Pagination pagination) {
    	if(pagination.getOrderBy() == null) {
    		return sort(collection, (String)null);
    	}
    	String property = pagination.getOrderBy();
    	if(pagination.getDirection()!= null) {
    		property = property + ":"  + pagination.getDirection();
    	}
    	return sort(collection, property);
    }

    /**
     * Sorts the collection on a single property.
     *
     * @param object the collection to be sorted.
     * @param property the property to sort on.
     */
    public List sort(List collection, String property)
    {
        if(property == null) {
        	return sort(collection, (List)null);
        }
    	List properties = new ArrayList(1);
        properties.add(property);

        return sort(collection, properties);
    }

    public List sort(List collection, List properties)
    {
    	if(logger.isDebugEnabled()) {
    		logger.debug("Sort Criteria is " + properties);
    	}
        return internalSort(collection, properties);
    }

    protected List internalSort(List list, List properties)
    {
        try
        {
            if (properties == null)
            {
            	return list;
                
            } else {
                Collections.sort(list, new PropertiesComparator(properties));
            }
            return list;
        }
        catch (Exception e)
        {
            //TODO: log this
            return null;
        }
    }


    /**
     * Does all of the comparisons
     */
    public static class PropertiesComparator
        implements Comparator, java.io.Serializable
    {
        private static final int TYPE_ASCENDING = 1;
        private static final int TYPE_DESCENDING = -1;


        List properties;
        int[] sortTypes;

        public PropertiesComparator(List props)
        {
            // copy the list so we can safely drop :asc and :desc suffixes
            this.properties = new ArrayList(props.size());
            this.properties.addAll(props);

            // determine ascending/descending
            sortTypes = new int[properties.size()];

            for (int i = 0; i < properties.size(); i++)
            {
                if (properties.get(i) == null)
                {
                    throw new IllegalArgumentException("Property " + i
                            + "is null, sort properties may not be null.");
                }

                // determine if the property contains a sort type
                // e.g "Name:asc" means sort by property Name ascending
                String prop = properties.get(i).toString();
                int colonIndex = prop.indexOf(':');
                if (colonIndex != -1)
                {
                    String sortType = prop.substring(colonIndex + 1);
                    properties.set(i, prop.substring(0, colonIndex));

                    if (SORT_ASCENDING.equalsIgnoreCase(sortType) || SORT_ASC.equalsIgnoreCase(sortType))
                    {
                        sortTypes[i] = TYPE_ASCENDING;
                    }
                    else if (SORT_DESCENDING.equalsIgnoreCase(sortType) || SORT_DES.equalsIgnoreCase(sortType))
                    {
                        sortTypes[i] = TYPE_DESCENDING;
                    }
                    else
                    {
                        //FIXME: log this
                        // invalide property sort type. use default instead.
                        sortTypes[i] = TYPE_ASCENDING;
                    }
                }
                else
                {
                    // default sort type is ascending.
                    sortTypes[i] = TYPE_ASCENDING;
                }
            }
        }

        public int compare(Object lhs, Object rhs)
        {
            for (int i = 0; i < properties.size(); i++)
            {
                int comparison = 0;
                String property = (String)properties.get(i);
                property = 	getPropertyNameWithoutSelfClassName(lhs, property);
				
				// properties must be comparable
                Comparable left = getComparable(lhs, property);
                Comparable right = getComparable(rhs, property);

                if(left == null && right == null) {
                	continue; 
                }
                if (left == null && right != null)
                {
                    // find out how right feels about left being null
                    comparison = 1;
//                    right.compareTo(null);
                    // and reverse that (if it works)
//                    comparison *= -1;
                } else if(left != null && right == null) {
                	comparison = -1;
                } else if (left instanceof String)
                {
                    //TODO: make it optional whether or not case is ignored
                    comparison = ((String)left).compareToIgnoreCase((String)right);
                }
                else 
                {
                    comparison = left.compareTo(right);
                }

                // return the first difference we find
                if (comparison != 0)
                {
                    // multiplied by the sort direction, of course
                    return comparison * sortTypes[i];
                }
            }
            return 0;
        }
        
        private static String  getPropertyNameWithoutSelfClassName(Object object,  String propertyName) {
        	if(propertyName != null && propertyName.indexOf(".") > 0 && object != null) {
        		String firstLevelProperty = propertyName.substring(0, propertyName.indexOf("."));
        		String objectName = object.getClass().getSimpleName();
        		if(objectName.equals(firstLevelProperty)) {
        			return propertyName.substring(propertyName.indexOf(".") + 1);
        		}
        	}
        	return propertyName;
        }
    }

    /**
     * Safely retrieves the comparable value for the specified property
     * from the specified object. Subclasses that wish to perform more
     * advanced, efficient, or just different property retrieval methods
     * should override this method to do so.
     */
    protected static Comparable getComparable(Object object, String property)
    {
        try
        {
            return (Comparable)PropertyUtils.getProperty(object, property);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Could not retrieve comparable value for '"
                                               + property + "' from " + object + ": " + e);
        }
    }

}
