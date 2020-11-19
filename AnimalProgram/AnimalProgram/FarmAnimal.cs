using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AnimalProgram
{
    abstract class FarmAnimal
    {
        //public properties
        public int id;
        public double amountWater;
        public double dailyCost;
        public double weight;
        public int age;
        public string color;
        public string type;

        //constructor
        public FarmAnimal(int id, double amountWater, double dailyCost, double weight, int age, string color, string type)
        {
            this.id = id;
            this.amountWater = amountWater;
            this.dailyCost = dailyCost;
            this.weight = weight;
            this.age = age;
            this.color = color;
            this.type = type;
        }

        //abstract method for displaying info
        public abstract string DisplayInfo();

        //abstract method for calculating animal profitablity
        public abstract double CalcAnimalProf();

        //abstract method to calculate animal tax
        public abstract double GetAnimalTax();

        //abstract method to get amount of milk
        public virtual double GetAmountMilk()
        {
            return 0;
        }
    }
}
