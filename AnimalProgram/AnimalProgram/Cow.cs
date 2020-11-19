using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AnimalProgram
{
    class Cow : FarmAnimal
    {
        //public properties
        public double amountMilk;

        //Constructor
        public Cow(int id, double amountWater, double dailyCost, double weight, int age, string color, double amountMilk, string type) :
            base(id, amountWater, dailyCost, weight, age, color, type)
        {
            this.amountMilk = amountMilk;
        }

        //method to display info
        public override string DisplayInfo()
        {
            string str = string.Format("Animal Type: {7}\nID: {0}\nAmount of Water: {1}\nDailyCost: {2}\nWeight: {3}\nAge: {4}\nColor: {5}\nAmount of Milk: {6}",
                id, amountWater, dailyCost, weight, age, color, amountMilk, type);
            return str;
        }

        //method to calc profitability
        public override double CalcAnimalProf()
        {
            double prof = 0.0;
            prof += amountMilk * Prices.cowMilkPrice;
            prof -= amountWater * Prices.waterPrice;
            prof -= dailyCost;
            return prof;
        }

        //method to calc animal tax
        public override double GetAnimalTax()
        {
            double taxPaid = 0.0;
            taxPaid =  weight * Prices.govtTax;
            return taxPaid;
        }

        //method to get amount of milk
        public override double GetAmountMilk()
        {
            double amountMilk = this.amountMilk;
            return amountMilk;
        }
    }
}
