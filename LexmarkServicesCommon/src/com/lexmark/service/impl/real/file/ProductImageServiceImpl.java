package com.lexmark.service.impl.real.file;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.result.ProductImageResult;
import com.lexmark.service.api.ProductImageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.Logger;


/**
 * Author: Sam Terrell <sterrell@lexmark.com>
 */
public class ProductImageServiceImpl implements ProductImageService {
    //private static final Log LOG = LogFactory.getLog(ProductImageServiceImpl.class);
	private static final Logger LOG = LogManager.getLogger(ProductImageServiceImpl.class);

    private static Properties PROPERTIES = new Properties();
    private static File propFile;
    private static long oldFileDate=0;
    private static void readProperties(){
        try {
            Properties p = new Properties();
            oldFileDate = propFile.lastModified();
            p.load(new FileReader(propFile));
            PROPERTIES = p;
        } catch (IOException e) {
            LOG.error("UNABLE TO READ PRODUCT IMAGES!",e);
        }
    }

    private static Properties getProperties() {
        try {
            if(propFile == null || PROPERTIES == null || propFile.lastModified() > oldFileDate) {
                InitialContext ctx = new InitialContext();
                propFile = new File(ctx.lookup("java:comp/env/product-image-properties-path").toString());
                if(propFile.exists() && propFile.canRead() && propFile.isFile()) {
                    readProperties();
                    return PROPERTIES;
                } else {
                    LOG.error("UNABLE TO READ PRODUCT IMAGES! (Unable to find or read file.)");
                }
            }
        } catch (NamingException e) {
            LOG.error("UNABLE TO READ PRODUCT IMAGES!",e);
        }
        return PROPERTIES;
    }

    @Override
    public ProductImageResult retrieveProductImageUrl(ProductImageContract contract) throws Exception {
        Properties images = getProperties();
        String url = images.getProperty(contract.getPartNumber(),null);
        if(url == null) url = images.getProperty("default", LexmarkConstants.PRODUCT_NOT_FOUND_IMAGE_URL);
        if(url != null) {
            ProductImageResult pir = new ProductImageResult();
            pir.setProductImageUrl(url);
            return pir;
        }
        return null;
    }

    @Override
    public ProductImageResult retrieveProductImageUrl(ProductImageContract contract, String portalContextPath) throws Exception {
        Properties images = getProperties();
        String url = images.getProperty(contract.getPartNumber(),null);
        if(url == null) url = images.getProperty("default",portalContextPath + LexmarkConstants.PRODUCT_NOT_FOUND_IMAGE_URL2);
        if(url != null) {
            ProductImageResult pir = new ProductImageResult();
            pir.setProductImageUrl(url);
            return pir;
        }
        return null;
    }

}
