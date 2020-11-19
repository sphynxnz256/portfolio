using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.OleDb;
using System.IO;

namespace AnimalProgram
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        //Global hash table
        Dictionary<int, FarmAnimal> allAnimals = new Dictionary<int, FarmAnimal>();
        private static Object thisLock = new Object();

        //method to read Cows table, create cow objects and add them to hash table
        private void ReadCowTable(OleDbConnection conn)
        {
            string q = "SELECT * FROM Cows";
            OleDbCommand cmd = new OleDbCommand(q, conn);
            using (OleDbDataReader reader = cmd.ExecuteReader())
            {
                while (reader.Read())
                {
                    FarmAnimal animal;
                    if (!bool.Parse(reader["Is jersy"].ToString()))
                    {//create a Cow object
                        animal = new Cow(int.Parse(reader["ID"].ToString()), double.Parse(reader["Amount of water"].ToString()),
                            double.Parse(reader["Daily cost"].ToString()), double.Parse(reader["Weight"].ToString()),
                            int.Parse(reader["Age"].ToString()), reader["Color"].ToString(), double.Parse(reader["Amount of milk"].ToString()), "Cow");
                        allAnimals.Add(int.Parse(reader["ID"].ToString()), animal);
                    }
                    else
                    {//create a Jersey Cow object
                        animal = new JerseyCow(int.Parse(reader["ID"].ToString()), double.Parse(reader["Amount of water"].ToString()),
                            double.Parse(reader["Daily cost"].ToString()), double.Parse(reader["Weight"].ToString()),
                            int.Parse(reader["Age"].ToString()), reader["Color"].ToString(), double.Parse(reader["Amount of milk"].ToString()), "Jersey Cow");
                        allAnimals.Add(int.Parse(reader["ID"].ToString()), animal);
                    }

                }
                reader.Close();
            }
        }//end ReadCowTable

        //method to read Dogs table, create Dog objects and add them to hash table
        private void ReadDogsTable(OleDbConnection conn)
        {
            string q = "SELECT * FROM Dogs";
            OleDbCommand cmd = new OleDbCommand(q, conn);
            using (OleDbDataReader reader = cmd.ExecuteReader())
            {
                while (reader.Read())
                {
                    FarmAnimal animal;
                    //create a Dog object
                    animal = new Dog(int.Parse(reader["ID"].ToString()), double.Parse(reader["Amount of water"].ToString()),
                        double.Parse(reader["Daily cost"].ToString()), double.Parse(reader["Weight"].ToString()),
                        int.Parse(reader["Age"].ToString()), reader["Color"].ToString(), "Dog");
                    allAnimals.Add(int.Parse(reader["ID"].ToString()), animal);
                }
                reader.Close();
            }
        }//end ReadDogsTable

        //method to read Goats table, create Goats objects and add them to hash table
        private void ReadGoatsTable(OleDbConnection conn)
        {
            string q = "SELECT * FROM Goats";
            OleDbCommand cmd = new OleDbCommand(q, conn);
            using (OleDbDataReader reader = cmd.ExecuteReader())
            {
                while (reader.Read())
                {
                    FarmAnimal animal;
                    //create a Goat Object
                    animal = new Goat(int.Parse(reader["ID"].ToString()), double.Parse(reader["Amount of water"].ToString()),
                        double.Parse(reader["Daily cost"].ToString()), double.Parse(reader["Weight"].ToString()),
                        int.Parse(reader["Age"].ToString()), reader["Color"].ToString(), double.Parse(reader["Amount of milk"].ToString()), "Goat");
                    allAnimals.Add(int.Parse(reader["ID"].ToString()), animal);
                }
                reader.Close();
            }
        }//end ReadGoatsTable

        //method to read Sheep table, create Sheep objects and add them to hash table
        private void ReadSheepTable(OleDbConnection conn)
        {
            string q = "SELECT * FROM Sheep";
            OleDbCommand cmd = new OleDbCommand(q, conn);
            using (OleDbDataReader reader = cmd.ExecuteReader())
            {
                while (reader.Read())
                {
                    FarmAnimal animal;
                    //create a Sheep Object
                    animal = new Sheep(int.Parse(reader["ID"].ToString()), double.Parse(reader["Amount of water"].ToString()),
                        double.Parse(reader["Daily cost"].ToString()), double.Parse(reader["Weight"].ToString()),
                        int.Parse(reader["Age"].ToString()), reader["Color"].ToString(), double.Parse(reader["Amount of wool"].ToString()), "Sheep");
                    allAnimals.Add(int.Parse(reader["ID"].ToString()), animal);
                }
                reader.Close();
            }
        }//end ReadSheepTable

        //method to read Commodity Prices table, add prices to price static object
        private void ReadPricesTable(OleDbConnection conn)
        {
            //read Commodity Prices table, add prices to price static object
            string q = "SELECT * FROM [Commodity Prices]";
            OleDbCommand cmd = new OleDbCommand(q, conn);
            Dictionary<string, double> pricesHT = new Dictionary<string, double>();
            using (OleDbDataReader reader = cmd.ExecuteReader())
            {
                while (reader.Read())
                {
                    pricesHT.Add(reader["Commodity"].ToString(), double.Parse(reader["Price"].ToString()));
                }
                reader.Close();
                Prices.goatMilkPrice = pricesHT["Goat milk price"];
                Prices.sheepWoolPrice = pricesHT["Sheep wool price"];
                Prices.waterPrice = pricesHT["Water price"];
                Prices.govtTax = pricesHT["Government tax per kg"];
                Prices.jerseyCowTax = pricesHT["Jersy cow tax"];
                Prices.cowMilkPrice = pricesHT["Cow milk price"];
            }
        }//end ReadPricesTable

        //method to read database and store the data as objects in a hashtable
        private void LoadDB()
        {
            string conn_string = "Provider=Microsoft.ACE.OLEDB.12.0;Data Source=" +
                @"C:\Users\Sphynxnz\Desktop\Stuff\wintec\Comp609\AnimalProgram(V2)\\FarmInfomation.accdb;" +
                "Persist Security Info = False";
            OleDbConnection conn = null;

            try
            {
                //connect to database
                conn = new OleDbConnection(conn_string);
                conn.Open();
            }
            catch (Exception ex) { MessageBox.Show(ex.Message); }

            //read tables and make objects
            ReadCowTable(conn);
            ReadDogsTable(conn);
            ReadGoatsTable(conn);
            ReadSheepTable(conn);
            ReadPricesTable(conn);

            conn.Close();//close connection to database
        }//end LoadDB

        //method to calc total farm profitability for day
        private void GetFarmProf()
        {
            double totalProf = 0.0;
            foreach (KeyValuePair<int, FarmAnimal> animal in allAnimals)
            {
                totalProf += animal.Value.CalcAnimalProf();
            }
            MessageBox.Show(string.Format("Farm Profitability: ${0:N2}", totalProf));
        }//end GetFarmProf

        //method to calc total tax paid for 1 month
        private void GetTaxMonth()
        {
            double totalTax = 0.0;
            foreach (KeyValuePair<int, FarmAnimal> animal in allAnimals)
            {
                totalTax += animal.Value.GetAnimalTax();
            }
            MessageBox.Show(string.Format("Tax for Month: ${0:N2}", totalTax * 30));
        }//end GetTaxMonth

        //method to get animal data
        private void GetAnimalData()
        {
            try
            {
                FarmAnimal animal = allAnimals[int.Parse(idTextBox.Text)];
                MessageBox.Show(animal.DisplayInfo());
            }
            catch { MessageBox.Show("ID not found"); }
        }//end GetAnimalData


        //method to get total milk from goats and cows per day
        private void GetDailyMilk()
        {
            double totalDailyMilk = 0.0;
            foreach (KeyValuePair<int, FarmAnimal> animal in allAnimals)
            {
                if (animal.Value.type == "Cow" || animal.Value.type == "Jersey Cow" || animal.Value.type == "Goat")
                {
                    totalDailyMilk += animal.Value.GetAmountMilk();
                }
            }
            MessageBox.Show(string.Format("Total Daily Milk: {0:N2}L", totalDailyMilk));
        }//end GetDailyMilk

        //method to find average age of all animals excluding dogs
        private void GetAvgAge()
        {

            double totalAge = 0.0;
            int count = 0;
            foreach (KeyValuePair<int, FarmAnimal> animal in allAnimals)
            {
                if (animal.Value.type != "Dog")
                {
                    totalAge += animal.Value.age;
                    count++;
                }
            }
            double avgAge = 0.0;
            if (count != 0)
            {
                avgAge = totalAge / count;
                
            }
            MessageBox.Show(string.Format("Average age of all farm animals (excluding Dogs): {0:N1}", avgAge));

        }//end GetAvgAge

        //method to find the average profit for cows and goats vs sheep
        private void GetAveProfMilkVsWool()
        {

            double totalMilkProf = 0.0;
            int countMilk = 0;
            double totalWoolProf = 0.0;
            int countWool = 0;
            foreach (KeyValuePair<int, FarmAnimal> animal in allAnimals)
            {
                //calc total milk profit for cows and goats
                if (animal.Value.type != "Dog" && animal.Value.type != "Sheep")
                {
                    totalMilkProf += animal.Value.CalcAnimalProf();
                    countMilk++;
                }

                //calc total woolprofit for sheep
                if (animal.Value.type == "Sheep")
                {
                    totalWoolProf += animal.Value.CalcAnimalProf();
                    countWool++;
                }
            }
            //calc avg profit for milk animals (cows and goats)
            double avgMilkProf = 0.0;
            if (countMilk != 0.0)
            {
                avgMilkProf = totalMilkProf / countMilk;
            }

            //calc avg profit for sheep
            double avgWoolProf = 0.0;
            if (countMilk != 0.0)
            {
                avgWoolProf = totalWoolProf / countWool;
            }

            MessageBox.Show(string.Format("Average Profit for Goats and Cows: ${0:N2}\nAverage Profit for Sheep: ${1:N2}", avgMilkProf, avgWoolProf));
        }//end GetAveProfMilkVsWool

        //method to calc ratio of cost of dogs vs cost of all animals
        private void GetDogToAnimalCostRatio()
        {
            double totalAnimalCost = 0.0;
            double totalDogCost = 0.0;
            foreach (KeyValuePair<int, FarmAnimal> animal in allAnimals)
            {
                //calc total animal cost
                totalAnimalCost += animal.Value.dailyCost;

                //calc total dog cost
                if (animal.Value.type == "Dog")
                {
                    totalDogCost += animal.Value.dailyCost;
                }
            }//end  GetDogToAnimalCostRatio

            //calc ratio of dog cost to all animal costs
            double dogToAnimalCostRatio = 0.0;
            if (totalAnimalCost != 0.0 && totalDogCost != 0.0)
            {
                dogToAnimalCostRatio = totalAnimalCost / totalDogCost;
                MessageBox.Show(string.Format("Ratio of Dog Cost vs All Animal Costs: $1.00 : ${0:N2}", dogToAnimalCostRatio));
            }
            else
            {
                MessageBox.Show("You have no Dogs and/or Animals");
            }
        }//end GetDogToAnimalCostRatio

        //method to calc ratio of red animals to all animals
        private void GetRedAnimalToAllAnimalRatio()
        {
            double redAnimalsCount = 0.0;
            double allAnimalsCount = 0.0;

            //red Animals and all animals
            foreach (KeyValuePair<int, FarmAnimal> animal in allAnimals)
            {
                allAnimalsCount++;
                if (animal.Value.color.ToLower() == "red")
                {
                    redAnimalsCount++;
                }
            }

            //calc ratio of red animals to all animals
            double redAnimalToAllAnimalRatio = 0.0;
            if (allAnimalsCount != 0.0 && redAnimalsCount != 0.0)
            {
                redAnimalToAllAnimalRatio = allAnimalsCount / redAnimalsCount;
                MessageBox.Show(string.Format("Ratio of Red Animals to All animals: 1.0 : {0:N1}", redAnimalToAllAnimalRatio));
            }
            else
            {
                MessageBox.Show("You have no Red Animals and/or any Animals");
            }
            
        }//end GetRedAnimalToAllAnimalRatio

        //method to calc amount of tax paid on jersey cows
        private void GetJerseyCowTaxPaid()
        {
            double jerseyTaxPaid = 0.0;
            foreach (KeyValuePair<int, FarmAnimal> animal in allAnimals)
            {
                if (animal.Value.type == "Jersey Cow")
                {
                    jerseyTaxPaid += animal.Value.GetAnimalTax();
                }
            }
            MessageBox.Show(string.Format("Amount of Jersey Cow Tax Paid: ${0:N2}", jerseyTaxPaid));
        }//end GetJerseyCowTaxPaid

        //method to find ratio of animals above age to all animals
        private void GetAgeRatio()
        {
            double ageCount = 0.0;
            double allAnimalCount = 0.0;
            bool errorflag = false;

            //count animals above age threshold and all animals
            foreach (KeyValuePair<int, FarmAnimal> animal in allAnimals)
            {
                allAnimalCount++;
                try
                {
                    if (int.Parse(RatioAgeTextBox.Text) <= 0 || int.Parse(RatioAgeTextBox.Text) > 50)
                    {
                        errorflag = true;
                        MessageBox.Show("Error Age input invalid");
                        break;

                    }
                    else if (animal.Value.age > int.Parse(RatioAgeTextBox.Text))
                    {
                        ageCount++;
                    }
                }
                catch
                {
                    MessageBox.Show("Error Age input invalid");
                    errorflag = true;
                    break;
                }
            }

            if (!errorflag)
            {
                //calc ratio of animals above threshold
                double ratio = 0.0;
                if (ageCount != 0.0 && allAnimalCount != 0.0)
                {
                    ratio = allAnimalCount / ageCount;
                    MessageBox.Show(string.Format("Ratio of Animals above age of {0}: 1.00 : {1:N2}", RatioAgeTextBox.Text, ratio));
                }
                else
                {
                    MessageBox.Show(string.Format("You have no Animals above the age of {0} and/or any Animals", RatioAgeTextBox.Text));
                }
            }
        }//end GetAgeRatio

        //method to make an array of all animal ids
        private int[] MakeIdArray()
        {
            int[] idArray = new int[allAnimals.Count];
            int i = 0;
            foreach (KeyValuePair<int, FarmAnimal> animal in allAnimals)
            {
                idArray[i] = animal.Value.id;
                i++;
            }
            return idArray;
        }
        //end MakeIdArray

        //method to sort array by profitability
        private void SortByProf(int[] idArray)
        {
            int temp = 0;
            bool swapped = true;
            //for every element in our array
            for (int i = 0; i < idArray.Length; i++)
            {
                swapped = false;
                //for every unsorted element in our array we will compare to
                for (int j = 1; j < idArray.Length - i; j++)
                {
                    //if profitability of object with array is greater than next object, swap them
                    if (allAnimals[idArray[j - 1]].CalcAnimalProf() > allAnimals[idArray[j]].CalcAnimalProf())
                    {
                        temp = idArray[j];
                        idArray[j] = idArray[j - 1];
                        idArray[j - 1] = temp;
                        swapped = true;
                    }
                }
                if (!swapped)
                {
                    break; //if elements were never swapped array must b sorted and we are done
                }
            }
        }//end SortByProf

        //method to write sorted hashtable to file
        private void WriteToFile(int[] idArray)
        {
            try
            {
                string filename = @"C:\Users\Sphynxnz\Desktop\Stuff\wintec\Comp609\AnimalProgram(V2)";
                StreamWriter writer = new StreamWriter(filename);
                writer.WriteLine("ID\tProfitability");
                writer.WriteLine("----\t-------------");
                foreach (int id in idArray)
                {
                    writer.WriteLine("{0}\t${1:N2}", id, allAnimals[id].CalcAnimalProf());
                }
                writer.Close();
                MessageBox.Show("ID's sorted by profitability saved to SortedHashtable.txt");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
                MessageBox.Show("Prossess failed");
            }
        }//end WriteToFile

        //method to print profitably ordered to a file
        private void PrintProfitabilyOrderedToFile()
        {
            int[] idArray = MakeIdArray();
            SortByProf(idArray);
            WriteToFile(idArray);
        }//end PrintProfitabilyOrderedToFile

        //this method loads the data from the data base into a hash table when the program launches
        //so that each method doesnt have to continuously reload the data each time it runs.
        private void Form1_Load(object sender, EventArgs e)
        {
            LoadDB();
        }//end Form1_Load

        //this method when the GoButton is clicked decides which radio button is checked
        //and runs the appropriate method for the checked radio button       
        private void GoButton_Click(object sender, EventArgs e)
        {
            if (singleAnimalRadio.Checked)
            {
                GetAnimalData();
            }
            if (farmProfRadio.Checked)
            {
                GetFarmProf();
            }
            if (totalAnimalTaxRadio.Checked)
            {
                GetTaxMonth();
            }
            if (dailyMilkRadio.Checked)
            {
                GetDailyMilk();
            }
            if (avgAgeRadio.Checked)
            {
                GetAvgAge();
            }
            if (profMilkVWoolRadio.Checked)
            {
                GetAveProfMilkVsWool();
            }
            if (ratioDogCostRadio.Checked)
            {
                GetDogToAnimalCostRatio();
            }
            if (ratioRedRadio.Checked)
            {
                GetRedAnimalToAllAnimalRatio();
            }
            if (totalTaxJerseyRadio.Checked)
            {
                GetJerseyCowTaxPaid();
            }
            if (ratioAgeRadio.Checked)
            {
                GetAgeRatio();
            }
            if (sortProfRadio.Checked)
            {
                PrintProfitabilyOrderedToFile();
            }
        }
    }//end class Form1 
}//end namespace AnimalProgram
