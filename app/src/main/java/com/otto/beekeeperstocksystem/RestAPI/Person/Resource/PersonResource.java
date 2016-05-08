package com.otto.beekeeperstocksystem.RestAPI.Person.Resource;
import java.io.Serializable;
/**
 * Created by Quam on 5/7/2016.
 */
public class PersonResource implements Serializable {

        private String serverId;
        private String firstName;
        private String emailAddress;
        private String lastName;
        private String token;

        public String getServerId() {
            return serverId;
        }



        public String getToken() {
            return token;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public String getLastName() {
            return lastName;
        }



        public PersonResource(Builder builder) {
            this.serverId = builder.serverId;
            this.firstName = builder.firstName;
            this.lastName = builder.lastName;

            this.emailAddress = builder.emailAddress;
            this.token = builder.token;

        }

        public static class Builder {
            private String serverId;
            private String firstName;
            private String emailAddress;
            private String lastName;

            private String token;


            public Builder serverId(String value) {
                this.serverId = value;
                return this;
            }



            public Builder firstName(String value) {
                this.firstName = value;
                return this;
            }

            public Builder token(String value) {
                this.token = value;
                return this;
            }

            public Builder emailAddress(String value) {
                this.emailAddress = value;
                return this;
            }

            public Builder lastName(String value) {
                this.lastName = value;
                return this;
            }



            public PersonResource build() {
                return new PersonResource(this);
            }
        }
    }

