(ns tile-soup.attrs
  (:require [clojure.spec.alpha :as s]))

(defn- str->num [s]
  #?(:clj (Double/parseDouble s)
     :cljs (let [n (js/parseFloat s)]
             (if (= n js/NaN)
               false
               n))))

(s/def ::version (s/and string? str->num))
(s/def ::name string?)

