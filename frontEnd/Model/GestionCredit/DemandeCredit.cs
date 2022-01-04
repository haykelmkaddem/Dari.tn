using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Model.GestionCredit
{
      public enum Profession { CADRE, ENSAIGNANT, GESTIONNAIRE, OUVRIER, TECHNICIEN, INFIRMIER, MEDECIN, INGENIEUR }
    public class DemandeCredit
    { 
        [JsonProperty("id")]
        [Key]
        public int id { set; get; }
        [JsonProperty("salaire")]
        public float salaire { get; set; }
        [JsonProperty("montantDemande")]
        public float montantDemande { set; get; }
        [JsonProperty("duree")]
        public int duree { set; get; }
        [JsonProperty("age")]
        public int age { set; get; }
        [JsonProperty("profession")]
        public Profession profesion { set; get; }
        public virtual ICollection<Bank> banks { get; set; }

    }
}
