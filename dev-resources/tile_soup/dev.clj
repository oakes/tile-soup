(ns tile-soup.dev
  (:require [clojure.java.io :as io]
            [tile-soup.core :as c]))

(defn read-public* [fname]
  (slurp (io/resource (str "public/" fname))))

(defmacro read-public [fname]
  (read-public* fname))

(defmacro parse-map [s]
  (list 'quote (c/parse-map (read-public* s))))

