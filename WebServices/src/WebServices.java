import services.MobServices;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gbsojo on 7/9/17.
 */
@ApplicationPath("/")
public class WebServices extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h= new HashSet<Class<?>>();
        h.add(MobServices.class);
        return h;
    }
}
