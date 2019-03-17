(ns tile-soup.utils
  (:require [clojure.spec.alpha :as s]
            [clojure.string :as str]
            [expound.alpha :as expound]))

(defn parse [spec content]
  (let [res (s/conform spec content)]
    (if (= ::s/invalid res)
      (throw (ex-info (expound/expound-str spec content) {}))
      res)))

(defn conformer [spec]
  (s/conformer
    (fn [x]
      (->> x
           (remove string?)
           (mapv #(parse (spec %) %))))))

(defn str->int* [s]
  #?(:clj (try
            (Integer/parseInt s)
            (catch Exception _ ::s/invalid))
     :cljs (let [n (js/parseInt s)]
             (if (js/isNaN n)
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

(defn str->boolean* [s]
  (let [ret (str->int* s)]
    (if (= ret ::s/invalid)
      ret
      (= ret 1))))

(def str->boolean (s/conformer str->boolean*))

