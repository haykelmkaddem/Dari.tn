
using Model.GestionUser;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;

namespace Service
{
    public class UserService
    {
        HttpClient httpClient;
        public UserService()
        {
            httpClient = new HttpClient();
            httpClient.BaseAddress = new Uri(Statics.baseAddress);
            httpClient.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

        }

        public String authenticate(UserDari u)
        {
            try
            {
                var APIResponse = httpClient.PostAsJsonAsync<UserDari>(Statics.baseAddress + "user/authenticate/", u).Result;



                if (APIResponse.IsSuccessStatusCode)
                {

                    String user = APIResponse.Content.ReadAsStringAsync().ToString();
                    return user;
                }

            }
            catch
            {
                return "user non authenticated";
            }
            return "u";
        }


        public IEnumerable<UserDari> GetAll()
        {

            var response = httpClient.GetAsync(Statics.baseAddress + "useradmin/findAll").Result;


            if (response.IsSuccessStatusCode)
            {

                var users = response.Content.ReadAsAsync<IEnumerable<UserDari>>().Result;
                return users;
            }
            return new List<UserDari>();

        }

        public UserDari GetById(int id)
        {

            UserDari user = null;

            var response = httpClient.GetAsync(Statics.baseAddress + "useradmin/findUser/" + id).Result;

            if (response.IsSuccessStatusCode)
            {
                var u = response.Content.ReadAsAsync<UserDari>().Result;

                return u;
            }


            return user;

        }

        public bool DeleteUser(int id)
        {

            try
            {
                var APIResponse = httpClient.DeleteAsync(Statics.baseAddress + "useradmin/delete/" + id);

                return true;
            }
            catch
            {
                return false;
            }


        }

    }
}
