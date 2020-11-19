using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AnimalProgram
{
    class Dog : FarmAnimal
    {
        //constructor
        public Dog(int id, double amountWater, double dailyCost, double weight, int age, string color, string type) :
            base(id, amountWater, dailyCost, weight, age, color, type)
        { }

        //method to display info
        public override string DisplayInfo()
        {
            string str = string.Format("Animal Type: Dog\nID: {0}\nAmount of Water: {1}\nDaily Cost: {2}\nWeight: {3}\nAge: {4}\nColor {5}",
                id, amountWater, dailyCost, weight, age, color);
            return str;
        }

        //method to calc profitability
        public override double CalcAnimalProf()
        {
            double prof = 0.0;
            prof -= amountWater * Prices.waterPrice;
            prof -= dailyCost;
            return prof;
        }

        //method to calc animal tax
        public override double GetAnimalTax()
        {
            double taxPaid = 0.0;
            taxPaid = weight * Prices.govtTax;
            return taxPaid;
        }
    }
}
