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

(defn str->long* [s]
  (cond
    (integer? s) s
    (not (string? s)) ::s/invalid
    :else
    (let [s (str/trim s)]
      #?(:clj (try
                (Long/parseLong s)
                (catch Exception _ ::s/invalid))
         :cljs (let [n (js/parseInt s)]
                 (if (js/isNaN n)
                   ::s/invalid
                   n))))))

(def str->long (s/conformer str->long*))

(defn str->float* [s]
  (cond
    (number? s) s
    (not (string? s)) ::s/invalid
    :else
    (let [s (str/trim s)]
      #?(:clj (try
                (Double/parseDouble s)
                (catch Exception _ ::s/invalid))
         :cljs (let [n (js/parseFloat s)]
                 (if (= n js/NaN)
                   ::s/invalid
                   n))))))

(def str->float (s/conformer str->float*))

(defn comma-str->vector* [s]
  (if-not (string? s)
    ::s/invalid
    (let [s (str/trim s)]
      (reduce
        (fn [v s]
          (conj v (parse str->long s)))
        []
        (str/split s #",")))))

(def comma-str->vector (s/conformer comma-str->vector*))

(defn str->boolean* [s]
  (let [ret (str->long* s)]
    (if (= ret ::s/invalid)
      ret
      (= ret 1))))

(def str->boolean (s/conformer str->boolean*))

