package me.serendipify.groupdiscoveryapptemplate.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.List;

/**
 * Implementation of SerendipifyService that uses Apache HTTP Client
 */
public class SerendipifyServiceHttpClientImpl implements SerendipifyService {

    private final String baseURL;

    public SerendipifyServiceHttpClientImpl(String baseURL) {
        this.baseURL = baseURL;
    }

    @Override
    public String createGroup(String group, String email) throws SerendipifyException {
        try {
            String request = baseURL + "group?createNewGroup=&groupName=" + group + "&groupOwnerEmail=" + email;
            Content returnContent = Request.Get(request).execute().returnContent();
            Gson gson = new GsonBuilder().create();
            CreateGroupResult result = gson.fromJson(returnContent.asString(), CreateGroupResult.class);
            if (result.isError()) {
                throw new SerendipifyException(result.getErrorMessage());
            }
            return result.getSessionID();
        } catch (IOException e) {
            throw new SerendipifyException("Failed to create a new group.", e);
        }
    }

    @Override
    public List<String> readInterests(String group, String session) throws SerendipifyException {
        return null;
    }

    @Override
    public void setPreferences(String group, String session, List<String> preferences) throws SerendipifyException {

    }
}
