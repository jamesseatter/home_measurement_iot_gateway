package eu.seatter.homeheating.edge.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 14/01/2019
 * Time: 11:37
 */
public class DeviceNotFound extends RuntimeException {
    public DeviceNotFound(String message) {
        super(message);
    }
}
