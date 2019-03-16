(ns tile-soup.utils
  (:require [clojure.spec.alpha :as s]
            [clojure.string :as str]))

(defn str->int* [s]
  #?(:clj (try
            (Integer/parseInt s)
            (catch Exception _ ::s/invalid))
     :cljs (let [n (js/parseInt s)]
             (if (= n js/NaN)
               ::s/invalid
               n))))

(def str->int (s/conformer str->int*))

(defn str->float* [s]
  #?(:clj (try
            (Double/parseDouble s)
            (catch Exception _ ::s/invalid))
     :cljs (let [n (js/parseFloat s)]
             (if (= n js/NaN)
               ::s/invalid
               n))))

(def str->float (s/conformer str->float*))

(defn comma-str->vector* [cs]
  (if-not (string? cs)
    ::s/invalid
    (reduce
      (fn [v s]
        (let [n (str->int* s)]
          (if (= n ::s/invalid)
            (reduced ::s/invalid)
            (conj v n))))
      []
      (str/split cs ","))))

(def comma-str->vector (s/conformer comma-str->vector))

