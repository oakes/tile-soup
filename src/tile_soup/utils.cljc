(ns tile-soup.utils
  (:require [clojure.spec.alpha :as s]))

(defn str->num [s]
  #?(:clj (Double/parseDouble s)
     :cljs (let [n (js/parseFloat s)]
             (if (= n js/NaN)
               false
               n))))

