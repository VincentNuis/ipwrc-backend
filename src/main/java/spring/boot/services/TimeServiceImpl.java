package spring.boot.services;

import kong.unirest.Unirest;
import org.springframework.stereotype.Service;
import spring.boot.model.TimeApiResponse;

@Service
public class TimeServiceImpl implements TimeService{

    @Override
    public String getCurrentTime(String timeZone){
         TimeApiResponse response = Unirest.get("http://worldtimeapi.org/api/timezone/Europe/" + timeZone)
                .asObject(TimeApiResponse.class).getBody();
        return response.getUtc_datetime();
    }
}
