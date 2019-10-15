using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MerchandGalaxy
{
    class Program
    {
        static void Main(string[] args)
        {
            String input = readInput();
            Console.WriteLine("INPUT:");
            Console.WriteLine(input);
            Console.WriteLine("\nOUTPUT:");
            Console.WriteLine(new GalaxyProcessor(input).answerQuestions());

        }

        private static String readInput()
        {
            StringBuilder sb = new StringBuilder("");
            sb.AppendLine("glob is I");
            sb.AppendLine("prok is V");
            sb.AppendLine("pish is X");

            sb.AppendLine("tegj is L");
            sb.AppendLine("glob glob Silver is 34 Credits");
            sb.AppendLine("glob prok Gold is 57800 Credits");
            sb.AppendLine("pish pish Iron is 3910 Credits");
            sb.AppendLine("how much is pish tegj glob glob ? ");
            sb.AppendLine("how many Credits is glob prok Silver ? ");
            sb.AppendLine("how many Credits is glob prok Gold ? ");
            sb.AppendLine("how many Credits is glob prok Iron ?");
            sb.AppendLine("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
            return sb.ToString();
        }
	
    }
}
