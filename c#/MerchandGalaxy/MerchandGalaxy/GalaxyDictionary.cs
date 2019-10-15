using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Text.RegularExpressions;

namespace MerchandGalaxy
{
    class GalaxyDictionary
    {
        private String CREDITS = "Credits";
        private String EQUALS = " is ";
        /**
         * Dictionary to romanNumbers or others
         */
        private Dictionary<String, Object> dictionary;

        /**
         * Entry is text without questions lines
         * 
         * @param text
         */
        public GalaxyDictionary(String text)
        {
            if (text != null)
            {
                dictionary = new Dictionary<String, Object>();
                loadDictionary(text);
            }
        }

        public Dictionary<String, Object> getDictionary()
        {
            return dictionary;
        }

        public void setDictionary(Dictionary<String, Object> dictionary)
        {
            this.dictionary = dictionary;
        }

        private void loadDictionary(String text)
        {
            String[] lines = text.Split('\n');
            List<String> listWithCredits = new List<String>();
            processLines(lines, listWithCredits);

            foreach (String line in listWithCredits)
            {
                String[] words = line.Split(' ');
                processLineWithCredits(words);

            }
        }

        private void processLines(String[] lines, List<String> listWithCredits)
        {
            foreach (String line in lines)
            {
                if (line.Trim().EndsWith(CREDITS))
                {
                    listWithCredits.Add(line);
                }
                else
                {
                    String[] wordValue = line.Split(new string[] { EQUALS }, StringSplitOptions.None);
                    if (wordValue.Length == 2)
                    {
                        String word = wordValue[0].Trim();
                        String value = wordValue[1].Trim();
                        if (RomanNumber.isRomanCaracter(value[0]))
                        {
                            dictionary.Add(word, value[0]);
                        }
                    }
                }
            }
        }

        private static bool isNumeric(String str)
        {

            try
            {
                Regex.Match(str, "\\d+(\\.\\d+)?");
            }
            catch (ArgumentException)
            {
                return false;
            }

            return true;
        }

        private void processLineWithCredits(String[] words)
        {
            String romanNumber = "";
            String newWord = "";
            for (int i = 0; i < words.Length; ++i)
            {
                String word = words[i].Trim();
                if (word.Trim().Equals(CREDITS))
                {
                    break;
                }

                if (word.Trim().Equals("is") && i > 0 && i < words.Length - 1)
                {
                    String value = words[i + 1].Trim();
                    if (isNumeric(value))
                    {
                        Double total = Double.Parse(value);

                        int totalRoman = new RomanNumber(romanNumber).romanToInteger();

                        if (romanNumber != null)
                        {
                            if (totalRoman != -1)
                            {
                                total = total / totalRoman;
                            }
                            else
                            {
                                break;// invalid entry roman number invalid next entry
                            }
                        }
                        dictionary.Add(newWord, total);
                    }
                    break;
                }

                if (dictionary.ContainsKey(word))
                {
                    romanNumber = romanNumber + dictionary[word].ToString();

                }
                else
                {
                    if (newWord.Length > 0 && i > 0)
                    {
                        String beforeWord = words[i - 1].Trim();
                        if (!dictionary.ContainsKey(beforeWord))
                        {
                            newWord = newWord + " " + word;
                        }
                        else
                        {
                            break; // invalid entry
                        }
                    }
                    else
                    {
                        newWord = word;
                    }

                }
            }

        }

        public Double calculateTotal(String value)
        {
            String romanValue = "";
            Double mult = 1.0;
            String[] words = value.Trim().Split(' ');
            Double total = -1;
            for (int i = 0; i < words.Length; ++i)
            {
                String word = words[i];
                if (dictionary.ContainsKey(word))
                {
                    Object number = dictionary[word];
                    if (number is Double && i == (words.Length - 1))
                    {
                        mult = (Double)number;
                    }
                    else if (number is Double)
                    {
                        romanValue = "";
                        break; // invalid entry
                    }
                    else
                    {
                        romanValue = romanValue + number;
                    }
                }
                else
                {
                    romanValue = "";
                    break; // invalid entry
                }

            }
            if (romanValue != "")
            {
                int totalRoman = new RomanNumber(romanValue).romanToInteger();
                if (totalRoman != -1)
                {
                    total = totalRoman * mult;
                }
            }
            return total;
        }


    }
    
}

