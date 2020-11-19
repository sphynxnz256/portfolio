using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AnimalProgram
{
    class JerseyCow : Cow
    {
        //constructor
        public JerseyCow(int id, double amountWater, double dailyCost, double weight, int age, string color, double amountMilk, string type) :
            base(id, amountWater, dailyCost, weight, age, color, amountMilk, type)
        { }

        //method to display info
        public override string DisplayInfo()
        {
            string str = string.Format("Animal Type: {7}\nID: {0}\nAmount of Water: {1}\nDailyCost: {2}\nWeight: {3}\nAge: {4}\nColor: {5}\nAmount of Milk: {6}",
                id, amountWater, dailyCost, weight, age, color, amountMilk, type);
            return str;
        }

        //method to calc animal tax
        public override double GetAnimalTax()
        {
            double taxPaid = 0.0;
            taxPaid = weight * Prices.jerseyCowTax;
            return taxPaid;
        }
    }
}
