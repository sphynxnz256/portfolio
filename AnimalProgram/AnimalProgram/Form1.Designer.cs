namespace AnimalProgram
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.idTextBox = new System.Windows.Forms.TextBox();
            this.RatioAgeTextBox = new System.Windows.Forms.TextBox();
            this.singleAnimalRadio = new System.Windows.Forms.RadioButton();
            this.label2 = new System.Windows.Forms.Label();
            this.farmProfRadio = new System.Windows.Forms.RadioButton();
            this.totalAnimalTaxRadio = new System.Windows.Forms.RadioButton();
            this.dailyMilkRadio = new System.Windows.Forms.RadioButton();
            this.avgAgeRadio = new System.Windows.Forms.RadioButton();
            this.profMilkVWoolRadio = new System.Windows.Forms.RadioButton();
            this.ratioDogCostRadio = new System.Windows.Forms.RadioButton();
            this.ratioRedRadio = new System.Windows.Forms.RadioButton();
            this.totalTaxJerseyRadio = new System.Windows.Forms.RadioButton();
            this.ratioAgeRadio = new System.Windows.Forms.RadioButton();
            this.sortProfRadio = new System.Windows.Forms.RadioButton();
            this.GoButton = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // idTextBox
            // 
            this.idTextBox.Location = new System.Drawing.Point(223, 37);
            this.idTextBox.Name = "idTextBox";
            this.idTextBox.Size = new System.Drawing.Size(100, 20);
            this.idTextBox.TabIndex = 2;
            // 
            // RatioAgeTextBox
            // 
            this.RatioAgeTextBox.Location = new System.Drawing.Point(223, 245);
            this.RatioAgeTextBox.Name = "RatioAgeTextBox";
            this.RatioAgeTextBox.Size = new System.Drawing.Size(100, 20);
            this.RatioAgeTextBox.TabIndex = 12;
            // 
            // singleAnimalRadio
            // 
            this.singleAnimalRadio.AutoSize = true;
            this.singleAnimalRadio.Location = new System.Drawing.Point(12, 38);
            this.singleAnimalRadio.Name = "singleAnimalRadio";
            this.singleAnimalRadio.Size = new System.Drawing.Size(207, 17);
            this.singleAnimalRadio.TabIndex = 14;
            this.singleAnimalRadio.TabStop = true;
            this.singleAnimalRadio.Text = "Get data about a single animal with ID:\r\n";
            this.singleAnimalRadio.UseVisualStyleBackColor = true;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(12, 9);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(345, 26);
            this.label2.TabIndex = 15;
            this.label2.Text = "Please pick an option from the following, enter any needed \r\ninfomation and press" +
    " the Go button:";
            // 
            // farmProfRadio
            // 
            this.farmProfRadio.AutoSize = true;
            this.farmProfRadio.Location = new System.Drawing.Point(12, 61);
            this.farmProfRadio.Name = "farmProfRadio";
            this.farmProfRadio.Size = new System.Drawing.Size(168, 17);
            this.farmProfRadio.TabIndex = 16;
            this.farmProfRadio.TabStop = true;
            this.farmProfRadio.Text = "Get the profitability for the farm";
            this.farmProfRadio.UseVisualStyleBackColor = true;
            // 
            // totalAnimalTaxRadio
            // 
            this.totalAnimalTaxRadio.AutoSize = true;
            this.totalAnimalTaxRadio.Location = new System.Drawing.Point(12, 84);
            this.totalAnimalTaxRadio.Name = "totalAnimalTaxRadio";
            this.totalAnimalTaxRadio.Size = new System.Drawing.Size(218, 17);
            this.totalAnimalTaxRadio.TabIndex = 17;
            this.totalAnimalTaxRadio.TabStop = true;
            this.totalAnimalTaxRadio.Text = "Get the tax paid on animals for the month";
            this.totalAnimalTaxRadio.UseVisualStyleBackColor = true;
            // 
            // dailyMilkRadio
            // 
            this.dailyMilkRadio.AutoSize = true;
            this.dailyMilkRadio.Location = new System.Drawing.Point(12, 107);
            this.dailyMilkRadio.Name = "dailyMilkRadio";
            this.dailyMilkRadio.Size = new System.Drawing.Size(128, 17);
            this.dailyMilkRadio.TabIndex = 18;
            this.dailyMilkRadio.TabStop = true;
            this.dailyMilkRadio.Text = "Get the total daily milk";
            this.dailyMilkRadio.UseVisualStyleBackColor = true;
            // 
            // avgAgeRadio
            // 
            this.avgAgeRadio.AutoSize = true;
            this.avgAgeRadio.Location = new System.Drawing.Point(12, 130);
            this.avgAgeRadio.Name = "avgAgeRadio";
            this.avgAgeRadio.Size = new System.Drawing.Size(173, 17);
            this.avgAgeRadio.TabIndex = 19;
            this.avgAgeRadio.TabStop = true;
            this.avgAgeRadio.Text = "Get the average age of animals";
            this.avgAgeRadio.UseVisualStyleBackColor = true;
            // 
            // profMilkVWoolRadio
            // 
            this.profMilkVWoolRadio.AutoSize = true;
            this.profMilkVWoolRadio.Location = new System.Drawing.Point(12, 153);
            this.profMilkVWoolRadio.Name = "profMilkVWoolRadio";
            this.profMilkVWoolRadio.Size = new System.Drawing.Size(184, 17);
            this.profMilkVWoolRadio.TabIndex = 20;
            this.profMilkVWoolRadio.TabStop = true;
            this.profMilkVWoolRadio.Text = "Get the profitability of milk vs wool";
            this.profMilkVWoolRadio.UseVisualStyleBackColor = true;
            // 
            // ratioDogCostRadio
            // 
            this.ratioDogCostRadio.AutoSize = true;
            this.ratioDogCostRadio.Location = new System.Drawing.Point(12, 176);
            this.ratioDogCostRadio.Name = "ratioDogCostRadio";
            this.ratioDogCostRadio.Size = new System.Drawing.Size(202, 17);
            this.ratioDogCostRadio.TabIndex = 21;
            this.ratioDogCostRadio.TabStop = true;
            this.ratioDogCostRadio.Text = "Get the ratio of dog cost vs total cost ";
            this.ratioDogCostRadio.UseVisualStyleBackColor = true;
            // 
            // ratioRedRadio
            // 
            this.ratioRedRadio.AutoSize = true;
            this.ratioRedRadio.Location = new System.Drawing.Point(12, 199);
            this.ratioRedRadio.Name = "ratioRedRadio";
            this.ratioRedRadio.Size = new System.Drawing.Size(151, 17);
            this.ratioRedRadio.TabIndex = 22;
            this.ratioRedRadio.TabStop = true;
            this.ratioRedRadio.Text = "Get the ratio of red animals";
            this.ratioRedRadio.UseVisualStyleBackColor = true;
            // 
            // totalTaxJerseyRadio
            // 
            this.totalTaxJerseyRadio.AutoSize = true;
            this.totalTaxJerseyRadio.Location = new System.Drawing.Point(12, 222);
            this.totalTaxJerseyRadio.Name = "totalTaxJerseyRadio";
            this.totalTaxJerseyRadio.Size = new System.Drawing.Size(196, 17);
            this.totalTaxJerseyRadio.TabIndex = 23;
            this.totalTaxJerseyRadio.TabStop = true;
            this.totalTaxJerseyRadio.Text = "Get the total tax paid on jersey cows";
            this.totalTaxJerseyRadio.UseVisualStyleBackColor = true;
            // 
            // ratioAgeRadio
            // 
            this.ratioAgeRadio.AutoSize = true;
            this.ratioAgeRadio.Location = new System.Drawing.Point(12, 245);
            this.ratioAgeRadio.Name = "ratioAgeRadio";
            this.ratioAgeRadio.Size = new System.Drawing.Size(211, 17);
            this.ratioAgeRadio.TabIndex = 24;
            this.ratioAgeRadio.TabStop = true;
            this.ratioAgeRadio.Text = "Get the ratio of animals over the age of:";
            this.ratioAgeRadio.UseVisualStyleBackColor = true;
            // 
            // sortProfRadio
            // 
            this.sortProfRadio.AutoSize = true;
            this.sortProfRadio.Location = new System.Drawing.Point(12, 268);
            this.sortProfRadio.Name = "sortProfRadio";
            this.sortProfRadio.Size = new System.Drawing.Size(311, 17);
            this.sortProfRadio.TabIndex = 25;
            this.sortProfRadio.TabStop = true;
            this.sortProfRadio.Text = "Generate a file containing the animal ids sorted by profitability";
            this.sortProfRadio.UseVisualStyleBackColor = true;
            // 
            // GoButton
            // 
            this.GoButton.Location = new System.Drawing.Point(122, 291);
            this.GoButton.Name = "GoButton";
            this.GoButton.Size = new System.Drawing.Size(75, 23);
            this.GoButton.TabIndex = 26;
            this.GoButton.Text = "Go";
            this.GoButton.UseVisualStyleBackColor = true;
            this.GoButton.Click += new System.EventHandler(this.GoButton_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(358, 330);
            this.Controls.Add(this.GoButton);
            this.Controls.Add(this.sortProfRadio);
            this.Controls.Add(this.ratioAgeRadio);
            this.Controls.Add(this.totalTaxJerseyRadio);
            this.Controls.Add(this.ratioRedRadio);
            this.Controls.Add(this.ratioDogCostRadio);
            this.Controls.Add(this.profMilkVWoolRadio);
            this.Controls.Add(this.avgAgeRadio);
            this.Controls.Add(this.dailyMilkRadio);
            this.Controls.Add(this.totalAnimalTaxRadio);
            this.Controls.Add(this.farmProfRadio);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.singleAnimalRadio);
            this.Controls.Add(this.RatioAgeTextBox);
            this.Controls.Add(this.idTextBox);
            this.Name = "Form1";
            this.ShowIcon = false;
            this.Text = "Animal Program";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.TextBox idTextBox;
        private System.Windows.Forms.TextBox RatioAgeTextBox;
        private System.Windows.Forms.RadioButton singleAnimalRadio;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.RadioButton farmProfRadio;
        private System.Windows.Forms.RadioButton totalAnimalTaxRadio;
        private System.Windows.Forms.RadioButton dailyMilkRadio;
        private System.Windows.Forms.RadioButton avgAgeRadio;
        private System.Windows.Forms.RadioButton profMilkVWoolRadio;
        private System.Windows.Forms.RadioButton ratioDogCostRadio;
        private System.Windows.Forms.RadioButton ratioRedRadio;
        private System.Windows.Forms.RadioButton totalTaxJerseyRadio;
        private System.Windows.Forms.RadioButton ratioAgeRadio;
        private System.Windows.Forms.RadioButton sortProfRadio;
        private System.Windows.Forms.Button GoButton;
    }
}

