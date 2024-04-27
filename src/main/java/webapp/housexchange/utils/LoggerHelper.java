package webapp.housexchange.utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
@Getter
@Setter
public class LoggerHelper {
    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LoggerHelper.class);

}
