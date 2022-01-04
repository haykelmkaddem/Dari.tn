using Newtonsoft.Json;
using System;

namespace Model.GestionUser
{
    public class RoleDari
    {
        [JsonProperty("id")]
        public long Id { get; set; }
        [JsonProperty("name")]
        public String Name { get; set; }
    }
}
