(ns tile-soup.core
  (:require [clojure.data.xml :as xml]
            [clojure.spec.alpha :as s]
            [clojure.walk :as walk]
            [expound.alpha :as expound]
            [tile-soup.map :as map]))

(defn- parse [spec content]
  (let [res (s/conform spec content)]
    (if (= ::s/invalid res)
      (throw (ex-info (expound/expound-str spec content) {}))
      res)))

(defn- record->map [r]
  (walk/postwalk
    (fn [x]
      (if (record? x)
        (into {} x)
        x))
    r))

(defn parse-map [tiled-map]
  (let [parsed #?(:clj  (xml/parse (java.io.StringReader. tiled-map))
                  :cljs (xml/parse-str tiled-map))
        parsed (record->map parsed)]
    (parse ::map/map (into {} parsed))))

