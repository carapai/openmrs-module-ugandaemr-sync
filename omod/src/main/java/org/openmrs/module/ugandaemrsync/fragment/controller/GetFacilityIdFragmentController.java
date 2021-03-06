/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * <p>
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.ugandaemrsync.fragment.controller;

import org.openmrs.module.appui.UiSessionContext;
import org.openmrs.module.ugandaemrsync.server.SyncConstant;
import org.openmrs.module.ugandaemrsync.server.SyncGlobalProperties;
import org.openmrs.module.ugandaemrsync.server.UgandaEMRHttpURLConnection;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.openmrs.ui.framework.page.PageModel;

/**
 *  * Controller for a fragment that shows all users  
 */
public class GetFacilityIdFragmentController {
	
	public void controller(UiSessionContext sessionContext, FragmentModel model) {
	}
	
	public void get(@SpringBean PageModel pageModel) throws Exception {
		SyncGlobalProperties properties = new SyncGlobalProperties();
		String serverProtocol = properties.getGlobalProperty(SyncConstant.SERVER_PROTOCOL);
		String serverIP = properties.getGlobalProperty(SyncConstant.SERVER_IP);
		
		UgandaEMRHttpURLConnection ugandaEMRHttpURLConnection = new UgandaEMRHttpURLConnection(serverProtocol, serverIP);
		
		String facilityId = ugandaEMRHttpURLConnection.requestFacilityId();
		
		if (facilityId != null) {
			properties.setGlobalProperty(SyncConstant.HEALTH_CENTER_SYNC_ID, facilityId);
			pageModel.put("message", "Facility ID generation successful");
		} else {
			pageModel.put("message", "Facility ID generation failed");
		}
		
	}
	
}
