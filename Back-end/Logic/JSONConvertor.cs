using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace GameSocket
{
   class JSONConvertor
    {


        public Gesture JsonToGesture(string Json)
        {
            JObject gestureJObject = JObject.Parse(Json);
            List<FVector> movement = new List<FVector>();

            foreach (JObject fVectorJObject in gestureJObject.GetValue("movement"))
            {
                
                movement.Add(new FVector((float)fVectorJObject.GetValue("x"), (float)fVectorJObject.GetValue("y"), (float)fVectorJObject.GetValue("z")));
            }

            return new Gesture((float)gestureJObject.GetValue("tolerance"), movement, (string)gestureJObject.GetValue("name"));

    
        }

        public String ObjectToJson(Gesture gesture)
        {
            return JsonConvert.SerializeObject(JObject.FromObject(gesture));
        }
    }
}
