using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MerchandGalaxy
{
    class GalaxyProcessor
    {
        private static String QUESTION = "?";
        private static String HOW_MUCH = "how much is";
        private static String HOW_MANY = "how many Credits is";
        private static String NO_IDEA = "I have no idea what you are talking about";
        private GalaxyDictionary dictionary;
        private List<String> questions;

        public GalaxyProcessor(String lines)
        {
            load(lines);
        }

        private void load(String lines)
        {
            if (lines != null && lines.Length > 0)
            {
                StringBuilder entryDictionary = new StringBuilder();
                questions = new List<String>();
                foreach (String line in lines.Split('\n'))
                {
                    if (line.Trim().EndsWith(QUESTION))
                    {
                        questions.Add(line);
                    }
                    else
                    {
                        entryDictionary.Append(line + "\n");
                    }
                }
                dictionary = new GalaxyDictionary(entryDictionary.ToString());
            }

        }

        public String answerQuestions()
        {
            if (questions != null)
            {
                StringBuilder resp = new StringBuilder();
                foreach (String question in questions)
                {
                    if (question.Trim().StartsWith(HOW_MUCH))
                    {
                        resp.Append(answerHowMuch(question) + "\n");
                    }
                    else if (question.Trim().StartsWith(HOW_MANY))
                    {
                        resp.Append(answerHowMany(question) + "\n");
                    }
                    else
                    {
                        resp.Append(NO_IDEA + "\n");
                    }
                }
                String response = resp.ToString();
                if (response.Length > 0)
                {
                    response = response.Substring(0, response.Length - 1);
                }
                return response;
            }

            return "";
        }

        private String answerHowMuch(String line)
        {

            String number = processString(line, HOW_MUCH);
            if (number != null && number.Length > 0)
            {
                Double total = dictionary.calculateTotal(number);
                if (total != -1)
                {
                    return number + " is " + total;
                }

            }

            return NO_IDEA;
        }

        private String answerHowMany(String line)
        {

            String number = processString(line, HOW_MANY);
            if (number != null && number.Length > 0)
            {
                Double total = dictionary.calculateTotal(number);
                if (total != -1)
                {
                    return number + " is " + total + " Credits";
                }

            }

            return NO_IDEA;
        }

        private String processString(String line, String how)
        {
            String question = line.Trim();
            question = question.Replace(QUESTION, "");
            question = question.Replace(how, "");
            question = question.Trim();
            return question;
        }

    }
}
