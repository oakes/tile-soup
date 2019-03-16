(ns tile-soup.core
  (:require [clojure.data.xml :as xml]
            [clojure.spec.alpha :as s]
            [expound.alpha :as expound]
            [tile-soup.map :as map]))

(defn- parse [spec content]
  (let [res (s/conform spec content)]
    (if (= ::s/invalid res)
      (throw (ex-info (expound/expound-str spec content) {}))
      res)))

(defn parse-map [tiled-map]
  (let [parsed #?(:clj  (xml/parse (java.io.StringReader. tiled-map))
                  :cljs (xml/parse-str tiled-map))]
    (parse ::map/map parsed)))

