package com.thoughtworks.youthzone.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.youthzone.PickProjectActivity;
import com.thoughtworks.youthzone.YouthZoneApp;
import com.thoughtworks.youthzone.helper.DatastoreFacade;
import com.thoughtworks.youthzone.helper.SalesforceFacade;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.ListView;

public class PickProjectActivityTest extends ActivityUnitTestCase<PickProjectActivity> {

	private Intent pickProjectIntent;

	private DatastoreFacade salesforceFacade;

	public PickProjectActivityTest() {
		super(PickProjectActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		salesforceFacade = mock(SalesforceFacade.class);
		List<String> projects = new ArrayList<>();
		projects.add("hello");
		when(salesforceFacade.getProjects()).thenReturn(projects);

		YouthZoneApp application = new YouthZoneApp();
		application.setDatastoreFacade(salesforceFacade);

		setApplication(application);

		pickProjectIntent = new Intent(null, PickProjectActivity.class);
	}

	public void testShouldLaunchPickMemberActivityWithIntent() {
		startActivity(pickProjectIntent, null, null);

		final ListView projectList = getActivity()
				.findViewById(com.thoughtworks.youthzone.R.id.projects_listview);

		projectList.performItemClick(projectList.getAdapter().getView(0, null, null), 0,
				projectList.getAdapter().getItemId(0));

		final Intent launchIntent = getStartedActivityIntent();
		assertNotNull("Intent was null", launchIntent);
		assertEquals("hello", (String) (projectList.getItemAtPosition(0)));
	}

}
