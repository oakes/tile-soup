(ns tile-soup.core
  (:require [clojure.data.xml :as xml]
            [clojure.spec.alpha :as s]
            [clojure.walk :as walk]
            [tile-soup.map :as map]
            [tile-soup.utils :as u]))

(defn- record->map [r]
  (walk/postwalk
    (fn [x]
      (if (record? x)
        (into {} x)
        x))
    r))

(defn parse [tiled-map]
  (let [parsed #?(:clj  (xml/parse (java.io.StringReader. tiled-map))
                  :cljs (xml/parse-str tiled-map))]
    (u/parse ::map/map (record->map parsed))))

