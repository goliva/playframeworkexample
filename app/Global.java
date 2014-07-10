import java.util.concurrent.TimeUnit;

import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.Akka;
import scala.concurrent.duration.FiniteDuration;


public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app) {
		Logger.info("Scheduling clear cache job.");
		Akka.system().scheduler().schedule(FiniteDuration.Zero(), FiniteDuration.create(10, TimeUnit.SECONDS), new Runnable() {
			@Override
			public void run() {
				Logger.info("Starting clear cache job.");
				Logger.info("Finished clear cache job.");
			}
		}, Akka.system().dispatcher());
	}
}
