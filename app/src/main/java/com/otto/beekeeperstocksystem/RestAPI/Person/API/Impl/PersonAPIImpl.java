package com.otto.beekeeperstocksystem.RestAPI.Person.API.Impl;

import com.google.gson.Gson;
import com.otto.beekeeperstocksystem.Conf.Util.AppUtil;
import com.otto.beekeeperstocksystem.Domain.Person;
import com.otto.beekeeperstocksystem.RestAPI.Person.API.PersonAPI;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Quam on 5/7/2016.
 */
public class PersonAPIImpl implements PersonAPI {

        private static final String postUrl = AppUtil.getBaserURI() + "api/droid/person/contact/post";
        private static final String updateUrl = AppUtil.getBaserURI() + "api/droid/person/contact/update";

        @Override
        public Person createPerson(Person contact) throws IOException {
            String json = new Gson().toJson(contact);
            RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
            Request request = new Request.Builder()
                    .url(postUrl)
                    .post(body)
                    .build();
            Response response = AppUtil.getConnection().newCall(request).execute();
            String value = response.body().string();
            Person personContact = new Gson().fromJson(value, Person.class);
            return personContact;
        }

        @Override
        public Person updatePerson(Person contact) throws IOException {
            String json = new Gson().toJson(contact);
            RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
            Request request = new Request.Builder()
                    .url(updateUrl)
                    .post(body)
                    .build();
            Response response = AppUtil.getConnection().newCall(request).execute();
            String value = response.body().string();
            Person person = new Gson().fromJson(value, Person.class);
            return person;
        }

}
