(ns tile-soup.utils
  (:require [clojure.spec.alpha :as s]))

(defn str->int* [s]
  #?(:clj (try
            (Integer/parseInt s)
            (catch Exception _ ::s/invalid))
     :cljs (let [n (js/parseInt s)]
             (if (= n js/NaN)
               ::s/invalid
               n))))

(def str->int (s/conformer str->int*))

