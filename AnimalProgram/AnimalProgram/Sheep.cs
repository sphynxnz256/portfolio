using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AnimalProgram
{
    class Sheep : FarmAnimal
    {
        //public properties
        public double amountWool;

        //Constructor
        public Sheep(int id, double amountWater, double dailyCost, double weight, int age, string color, double amountWool, string type) :
            base(id, amountWater, dailyCost, weight, age, color, type)
        {
            this.amountWool = amountWool;
        }

        //method to display info
        public override string DisplayInfo()
        {
            string str = string.Format("Animal Type: {7}\nID: {0}\nAmount of Water: {1}\nDaily Cost: {2}\nWeight: {3}\nAge: " +
                "{4}\nColor: {5}\nAmount of Wool: {6}", id, amountWater, dailyCost, weight, age, color, amountWool, type);
            return str;
        }

        //method to calc profitability
        public override double CalcAnimalProf()
        {
            double prof = 0.0;
            prof += amountWool * Prices.sheepWoolPrice;
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
