package fhq.service;

import java.util.Map;

public interface SubscribeService {
    Map<String,Object> subscribe(Integer userID, Integer comicId);

}
