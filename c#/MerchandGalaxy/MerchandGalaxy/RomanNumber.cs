using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Text.RegularExpressions;


namespace MerchandGalaxy
{
    class RomanNumber
    {
        private static String regexRomanNumber = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
        private static List<Char> romansCharacters = (new Char[] { 'I', 'V', 'X', 'L', 'C', 'D', 'M' }).ToList();       

        public String value;

        public RomanNumber(String value)
        {
            this.setValue(value);
        }

        public String getValue()
        {
            return value;
        }

        public void setValue(String value)
        {
            this.value = value;
        }

        public bool isValid()
        {
            if (this.getValue() == null || this.getValue().Length == 0)
            {
                return false;
            }

            try
            {
                Regex.Match(this.getValue(), regexRomanNumber);
            }
            catch (ArgumentException)
            {
                return false;
            }

            return true;          
            
        }

        public int romanToInteger()
        {
            if (isValid())
            {
                int number = 0;
                int lastNumber = 0;
                for (int i = getValue().Length - 1; i >= 0; i--)
                {
                    char character = getValue()[i];                       

                    switch (character)
                    {
                        case 'M':
                            number = processNumber(1000, lastNumber, number);
                            lastNumber = 1000;
                            break;

                        case 'D':
                            number = processNumber(500, lastNumber, number);
                            lastNumber = 500;
                            break;

                        case 'C':
                            number = processNumber(100, lastNumber, number);
                            lastNumber = 100;
                            break;

                        case 'L':
                            number = processNumber(50, lastNumber, number);
                            lastNumber = 50;
                            break;

                        case 'X':
                            number = processNumber(10, lastNumber, number);
                            lastNumber = 10;
                            break;

                        case 'V':
                            number = processNumber(5, lastNumber, number);
                            lastNumber = 5;
                            break;

                        case 'I':
                            number = processNumber(1, lastNumber, number);
                            lastNumber = 1;
                            break;
                    }
                }
                return number;
            }
            return -1;
        }

        private int processNumber(int number, int lastNumber, int beforeNumber)
        {
            if (lastNumber > number)
            {
                return beforeNumber - number;
            }
            else
            {
                return beforeNumber + number;
            }
        }

        public static bool isRomanCaracter(Char c)
        {
            return romansCharacters.Contains(c);

        }

    }
}
