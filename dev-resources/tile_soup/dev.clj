(ns tile-soup.dev
  (:require [clojure.java.io :as io]))

(defmacro read-public [fname]
  (slurp (io/resource (str "public/" fname))))

