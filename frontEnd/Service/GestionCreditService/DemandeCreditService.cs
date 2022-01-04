using Model.GestionCredit;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;

namespace Service.GestionCreditService
{
    public class DemandeCreditService
    {
        HttpClient httpClient;
        public DemandeCreditService()
        {
            httpClient = new HttpClient();
            httpClient.BaseAddress = new Uri(Statics.baseAddress);
            httpClient.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

        }
        public Boolean AjouterDemande(DemandeCredit b)
        {
            try
            {
                var APIResponse = httpClient.PostAsJsonAsync<DemandeCredit>(Statics.baseAddress + "/DemandeCredit/ajouterDemande", b).ContinueWith(postTask => postTask.Result.EnsureSuccessStatusCode());
                System.Diagnostics.Debug.WriteLine(APIResponse.Result);
                return true;
            }
            catch
            {
                return false;
            }
        }
        public bool UpdateDemande(int id, DemandeCredit demande)
        {
            try
            {
                var APIResponse = httpClient.PutAsJsonAsync<DemandeCredit>(Statics.baseAddress + "/DemandeCredit/modifierDemande/" + id, demande).ContinueWith(postTask => postTask.Result.EnsureSuccessStatusCode());
                System.Diagnostics.Debug.WriteLine(APIResponse.Result);
                return true;
            }
            catch
            {
                return false;
            }
        }
        public IEnumerable<DemandeCredit> GetAllDemandeCredits()
        {

            var response = httpClient.GetAsync(Statics.baseAddress + "/DemandeCredit/getAlldemands").Result;


            if (response.IsSuccessStatusCode)
            {

                var DemandeCredit = response.Content.ReadAsAsync<IEnumerable<DemandeCredit>>().Result;
                return DemandeCredit;
            }
            return new List<DemandeCredit>();

        }
        public DemandeCredit GetDemandeById(int id)
        {

            DemandeCredit demandeC = null;

            var response = httpClient.GetAsync(Statics.baseAddress + "/DemandeCredit/getDemandebyId/" + id).Result;

            if (response.IsSuccessStatusCode)
            {
                var demande
                    = response.Content.ReadAsAsync<DemandeCredit>().Result;

                return demande;
            }


            return demandeC;

        }
        public bool DeleteDemande(int id)
        {

            try
            {
                var APIResponse = httpClient.DeleteAsync(Statics.baseAddress + "/DemandeCredit/DeleteDemande/" + id);

                return true;
            }
            catch
            {
                return false;
            }


        }
       

    }
}
