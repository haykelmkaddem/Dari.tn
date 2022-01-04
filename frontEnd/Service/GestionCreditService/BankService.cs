using Model.GestionCredit;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;

namespace Service.GestionCreditService
{
    public class BankService
    {

        HttpClient httpClient;
        public BankService()
        {
            httpClient = new HttpClient();
            httpClient.BaseAddress = new Uri(Statics.baseAddress);
            httpClient.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

        }
        public Boolean AjouterBank(Bank b)
        {
            try
            {
                var APIResponse = httpClient.PostAsJsonAsync<Bank>(Statics.baseAddress + "/Bank/ajouterBank", b).ContinueWith(postTask => postTask.Result.EnsureSuccessStatusCode());
                System.Diagnostics.Debug.WriteLine(APIResponse.Result);
                return true;
            }
            catch
            {
                return false;
            }
        }
        public bool updateBank(int id, Bank bnk)
        {
            try
            {
                var APIResponse = httpClient.PutAsJsonAsync<Bank>(Statics.baseAddress + "/Bank/modifierBank/" + id, bnk).ContinueWith(postTask => postTask.Result.EnsureSuccessStatusCode());
                System.Diagnostics.Debug.WriteLine(APIResponse.Result);
                return true;
            }
            catch
            {
                return false;
            }
        }

        public IEnumerable<Bank> GetAllBanks()
        {

            var response = httpClient.GetAsync(Statics.baseAddress + "/Bank/getBanks").Result;


            if (response.IsSuccessStatusCode)
            { 
              var Lis = response.Content.ReadAsAsync<IEnumerable<Bank>>().Result;
                return Lis;
            }
            return new List<Bank>();

        }
        public Bank GetBankById(int id)
        {

            Bank bank = null;

            var response = httpClient.GetAsync(Statics.baseAddress + "/Bank/getBankbyId/" + id).Result;

            if (response.IsSuccessStatusCode)
            {
                var b
                    = response.Content.ReadAsAsync<Bank>().Result;

                return b;
            }


            return bank;

        }
        public bool DeleteBank(int id)
        {

            try
            {
                var APIResponse = httpClient.DeleteAsync(Statics.baseAddress + "/Bank/BankDelete/" + id);

                return true;
            }
            catch
            {
                return false;
            }


        }
        public IEnumerable<Bank> GetBanksByDemande(int idDemande)
        {

            var response = httpClient.GetAsync(Statics.baseAddress+"/Bank/getBankbyDemande/"+ idDemande).Result;


            if (response.IsSuccessStatusCode)
            {
                var Lis = response.Content.ReadAsAsync<IEnumerable<Bank>>().Result;
                return Lis;
            }
            return new List<Bank>();

        }
        public IEnumerable<Bank> GetBanksApprouvee()
        {

            var response = httpClient.GetAsync(Statics.baseAddress + "").Result;


            if (response.IsSuccessStatusCode)
            {
                var Lis = response.Content.ReadAsAsync<IEnumerable<Bank>>().Result;
                return Lis;
            }
            return new List<Bank>();

        }
        public Boolean CalculerCreditPotentielle(int IdDemande)
        {
            try
            {
                var APIResponse = httpClient.PutAsync(Statics.baseAddress + "Bank/calculerCreditPotentielle/" + IdDemande,null);
                System.Diagnostics.Debug.WriteLine(APIResponse.Result);
                return true;
            }
            catch
            {
                return false;
            }


        }
        public Boolean calculerMarge(int IdDemande)
        {
            try
            {
                var APIResponse = httpClient.PutAsync(Statics.baseAddress + "Bank/calculerMarge/" + IdDemande,null);
                System.Diagnostics.Debug.WriteLine(APIResponse.Result);
                return true;
            }
            catch
            {
                return false;
            }


        }
        public Boolean calculerOffre(int IdDemande)
        {
            try
            {
                var APIResponse = httpClient.PutAsync(Statics.baseAddress + "Bank/calculerMarge/" + IdDemande,null);
                System.Diagnostics.Debug.WriteLine(APIResponse.Result);
                return true;
            }
            catch
            {
                return false;
            }


        }
        public Boolean ApprouverBank(int id)
        {
            try
            {
                var APIResponse = httpClient.PutAsync(Statics.baseAddress + "/Bank/approuverBank/" +id,null);
                System.Diagnostics.Debug.WriteLine(APIResponse.Result);
                return true;
            }
            catch
            {
                return false;
            }


        }
        public Bank MeilleurBank(int idDemande)
        {

            Bank bank = null;

            var response = httpClient.GetAsync(Statics.baseAddress + "/DemandeCredit/MeuilleurBanque/" + idDemande).Result;

            if (response.IsSuccessStatusCode)
            {
                 bank
                    = response.Content.ReadAsAsync<Bank>().Result;

                return bank;
            }


            return bank;

        }
    }
}
