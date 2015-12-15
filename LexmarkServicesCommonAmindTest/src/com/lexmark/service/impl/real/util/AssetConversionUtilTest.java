package com.lexmark.service.impl.real.util;

import static com.lexmark.service.impl.real.util.AssetConversionUtil.populateFavoriteFlag;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import com.lexmark.domain.Asset;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetDo;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetFavoriteDo;

/**
 * @author vpetruchok
 * @version 1.0, 2012-05-10
 */
public class AssetConversionUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testPopulateFavoriteFlag() throws Exception {
        {
            Asset a = new Asset();
            populateFavoriteFlag(a, new OrderSuppliesAssetFavoriteDo(), OrderSuppliesAssetFavoriteDo.class, null);
            assertEquals(true, a.getUserFavoriteFlag());
        }
        {
            Asset a = new Asset();
            populateFavoriteFlag(a, new OrderSuppliesAssetDo(), OrderSuppliesAssetFavoriteDo.class, null);
            assertEquals(false, a.getUserFavoriteFlag());
        }
        
        {
            String assetId = "a-1";
            Asset a = new Asset(); 
            HashSet<String> favSet = new HashSet<String>();
            favSet.add(assetId);
            OrderSuppliesAssetDo assetBase = new OrderSuppliesAssetDo();
              assetBase.setAssetId(assetId);
            populateFavoriteFlag(a, assetBase, OrderSuppliesAssetFavoriteDo.class, favSet);
            assertEquals(true, a.getUserFavoriteFlag());
        }
        
        {
            String assetId = "a-1";
            Asset a = new Asset(); 
            OrderSuppliesAssetDo assetBase = new OrderSuppliesAssetDo();
            assetBase.setAssetId(assetId);
            
            populateFavoriteFlag(a, assetBase, OrderSuppliesAssetFavoriteDo.class, Arrays.asList(assetId, assetId));
            assertEquals(true, a.getUserFavoriteFlag());
            
            populateFavoriteFlag(a, assetBase, OrderSuppliesAssetFavoriteDo.class, null);
            assertEquals(false, a.getUserFavoriteFlag());
        }
        
    }

}
