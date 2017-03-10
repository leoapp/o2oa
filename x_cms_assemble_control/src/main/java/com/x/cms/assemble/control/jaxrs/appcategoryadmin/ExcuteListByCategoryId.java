package com.x.cms.assemble.control.jaxrs.appcategoryadmin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.x.base.core.cache.ApplicationCache;
import com.x.base.core.http.ActionResult;
import com.x.base.core.http.EffectivePerson;
import com.x.base.core.logger.Logger;
import com.x.base.core.logger.LoggerFactory;
import com.x.base.core.utils.SortTools;
import com.x.cms.assemble.control.WrapTools;
import com.x.cms.core.entity.AppCategoryAdmin;

import net.sf.ehcache.Element;

public class ExcuteListByCategoryId extends ExcuteBase {

	private Logger logger = LoggerFactory.getLogger( ExcuteListByCategoryId.class );
	
	@SuppressWarnings("unchecked")
	protected ActionResult<List<WrapOutAppCategoryAdmin>> execute( HttpServletRequest request, EffectivePerson effectivePerson, String categoryId ) throws Exception {
		ActionResult<List<WrapOutAppCategoryAdmin>> result = new ActionResult<>();
		List<WrapOutAppCategoryAdmin> wraps = null;
		List<String> ids = null;
		List<AppCategoryAdmin> appCategoryAdminList = null;
		Boolean check = true;
		
		String cacheKey = ApplicationCache.concreteCacheKey( "category", categoryId );
		Element element = cache.get(cacheKey);
		
		if ((null != element) && ( null != element.getObjectValue()) ) {
			wraps = ( List<WrapOutAppCategoryAdmin> ) element.getObjectValue();
			result.setData(wraps);
		} else {
			if( check ){
				if( categoryId == null || categoryId.isEmpty() ){
					check = false;
					Exception exception = new AppCategoryAdminCategoryIdEmptyException();
					result.error( exception );
					logger.error( exception, effectivePerson, request, null);
				}
			}
			if( check ){
				try {
					ids = appCategoryAdminServiceAdv.listAppCategoryIdByCategoryId( categoryId );
				} catch (Exception e) {
					check = false;
					Exception exception = new AppCategoryAdminListByCategoryIdException( e, categoryId );
					result.error( exception );
					logger.error( exception, effectivePerson, request, null);
				}
			}
			if( check ){
				if( ids != null && !ids.isEmpty() ){
					try {
						appCategoryAdminList = appCategoryAdminServiceAdv.list( ids );
					} catch (Exception e) {
						check = false;
						Exception exception = new AppCategoryAdminListByIdsException( e );
						result.error( exception );
						logger.error( exception, effectivePerson, request, null);
					}
				}
			}
			if( check ){
				if( appCategoryAdminList != null && !appCategoryAdminList.isEmpty() ){
					try {
						wraps = WrapTools.appCategoryAdmin_wrapout_copier.copy( appCategoryAdminList );
						SortTools.desc( wraps, "sequence");
						cache.put(new Element( cacheKey, wraps ));
						result.setData(wraps);
					} catch (Exception e) {
						Exception exception = new AppCategoryAdminWrapOutException( e );
						result.error( exception );
						logger.error( exception, effectivePerson, request, null);
					}
				}
			}
		}		
		return result;
	}
	
}