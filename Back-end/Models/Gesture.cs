using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace GameSocket
{
    class Gesture
    {
        private List<FVector> movement;
        private float tolerance;
        private String name;

        public List<FVector> Movement
        {
            get { return movement; }
            set { movement = value; }
        }

        public float Tolerance
        {
            get { return tolerance; }
            set { tolerance = value; }
        }

        public string Name
        {
            get { return name; }
            set { name = value; }
        }

        public Gesture() { }

        public Gesture(float tolerance, List<FVector> movement, String name)
        {
            this.movement = movement;
            this.tolerance = tolerance;
            this.name = name;
        }

    }
}
