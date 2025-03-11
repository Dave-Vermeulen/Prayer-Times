(ns prayer-times.core
  (:require [clj-http.client :as client]
            [cheshire.core :as json]
            [hiccup.page :as hiccup]))

(defn fetch-prayer-times []
  (let [url "http://api.aladhan.com/v1/timingsByCity"
        params {:query-params {:city "Cape Town"
                               :country "South Africa"
                               :method "2"}} ;;islamic society of North America

        response (client/get url params)
        body (json/parse-string (:body response) true)] ; parse json to Clojure map
    (get-in body [:data :timings]))) ; extract the timings

(def prayer-order ["Fajr" "Sunrise" "Dhuhr" "Asr" "Maghrib" "Isha"])

;; make some html for the prayer times
(defn generate-html [timings]
  (hiccup/html5
    [:head 
     [:meta {:charset "UTF-8"}]
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
     [:title "Cape Town Prayer Times"]
     [:style "
      body {
             font-family: 'Helvetica Neue', sans-serif;
             background: linear-gradient(to bottom right, #fcd0a1, #b1b695);
             padding: 2rem;
             color: #63535b;
             }
             .container {
             max-width: 800px;
             margin: 0 auto;
             background: rgba(255, 255, 255, 0.9);
             border-radius: 15px;
             padding: 2rem;
             box-shadow: 0 4px 6px rgba(0,0,0,0.1);
             }
             h1 {
             color: #6d1a36;
             text-align: center;
             border-bottom: 2px solid #53917e;
             padding-bottom: 1rem;
             }
             table {
             width: 100%;
             border-collapse: collapse;
             margin: 2rem 0;
             }
             th {
             background: #53917e;
             color: white;
             padding: 1rem;
             }
             td {
             padding: 1rem;
             border-bottom: 1px solid #b1b695;
             }
             tr:nth-child(even) {
             background: #f8f8f8;
             }
             .prayer-text {
             background: #fcd0a1;
             padding: 1.5rem;
             border-radius: 10px;
             margin-top: 2rem;
             font-size: 0.9em;
             }
             .arabic {
             font-size: 1.2em;
             text-align: right;
             direction: rtl;
             margin: 1rem 0;
             }"]]

    [:body
     [:div.container
     [:h1 "🕌 Cape Town Prayer Times"]
     [:table 
      [:thead
       [:tr
        [:th "Prayer"] [:th "Time"]]]
      [:tbody
       (for [prayer prayer-order
             :let [time (get timings prayer)]]
         [:tr
          [:td prayer] [:td time]])]]
     
     [:div.prayer-text
      [:h2 {:style "color: #6d1a36;"} "The Prophet's Prayer (ﷺ)"]
     [:div.arabic
      "بسم الله الرحمن الرحيم<br/>
       اللهم إنّي أسألك بأنّي أشهد أنك أنت الله لا إله إلا أنت الأحد الصمد الذي لم يلد و لم يولد و لم يكن له كفوً أحد"]
     [:h3 "Pronunciation:"]
     "ALLAHUMMA INNI AS ALUKA BI ANNI ASH-HADDU INNAKA ANTALLA-HA LA ILAHA ILLA ANTAL AHADUS-SAMAD-ALLAZEE LAM YA LID WA LAM YOO LAD WA LAM YA KULLAHOO KUFUWWAN AHAD"

     [:h3 "Translation:"]
     "O ALLAH, I PRAY TO YOU ON THE STRENGTH THAT I BEAR WITNESS THAT YOU ALONE ARE ALLAH AND THAT THERE IS NONE WORTHY OF WORSHIP EXCEPT YOU. YOU ARE ONE AND INDEPENDENT. NO ONE WAS BORN OF YOU NOR WERE YOU BORN TO ANYONE AND THERE IS NONE THE EQUAL OF YOU."

     [:p {:style "margin-top: 1rem; font-style: italic;"}
      "After this say: La ilaha illa Anta Subhanaka Inni Kuntu Mina Dzhaaalimeen." [:br]
      "Translation: There is no deity except You, exalted are You. Indeed, I have been of the wrongdoers."[:br]
       "Then Say: Ya Hannan Ya Mannan Ya Dhū-l-Jalāli wa-l-’ikrām" [:br]
       "Translation: O Most Gracious, O Bestower, O Possessor of Majesty and Honor."]]]]))


(defn -main []
  (let [timings (fetch-prayer-times)]
    (spit "prayer_times.html" (generate-html timings))
  (println "✨ Beautiful HTML generated!")))
