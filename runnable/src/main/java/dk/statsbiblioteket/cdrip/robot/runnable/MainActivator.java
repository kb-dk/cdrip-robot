package dk.statsbiblioteket.cdrip.robot.runnable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import dk.statsbiblioteket.cdrip.robot.Main;

public class MainActivator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("hello");
		Main.main(new String[0]);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("goodbye");
	}
}
