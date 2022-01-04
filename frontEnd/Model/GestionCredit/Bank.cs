using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Model.GestionCredit
{ public enum Banque {BIAT,ATB,ATTIJARI,BNA,BT,BH,STB}
   public class Bank
    {
        [JsonProperty("id")]
        [Key]
        public int id { set; get; }
        [JsonProperty("creditPotentielle")]
        public float CreditPotentielle { set; get; }
        [JsonProperty("marge")]
        public float marge { set; get; }
        [JsonProperty("offre")]
        public float offre { set; get; }
        [JsonProperty("teaux")]
        public float teaux { set; get; }
        [JsonProperty("approuvation")]
        public String approuvation { set; get; }
        [JsonProperty("nom")]
        public Banque NomBank { set;get; }
       [JsonProperty("demandeId")]
        public DemandeCredit demandecredit { set; get; }


    }
}
