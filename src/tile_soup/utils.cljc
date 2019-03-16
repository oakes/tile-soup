(ns tile-soup.utils
  (:require [clojure.spec.alpha :as s]))

(defn str->num* [s]
  #?(:clj (try
            (Double/parseDouble s)
            (catch Exception _ ::s/invalid))
     :cljs (let [n (js/parseFloat s)]
             (if (= n js/NaN)
               ::s/invalid
               n))))

(def str->num (s/conformer str->num*))

