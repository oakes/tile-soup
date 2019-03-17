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

(def ^:private ^:const horiz-bit 32)
(def ^:private ^:const vert-bit 31)
(def ^:private ^:const diag-bit 30)
(def ^:private ^:const unknown-bit 29)

(defn- get-bit [n k]
  (bit-and (bit-shift-right n k) 1))

(defn tile-id->map [id]
  (let [horiz? (= 1 (get-bit id horiz-bit))
        vert? (= 1 (get-bit id vert-bit))
        diag? (= 1 (get-bit id diag-bit))
        id (reduce bit-clear id (map dec [horiz-bit vert-bit diag-bit unknown-bit]))]
    {:horizontal? horiz?
     :vertical? vert?
     :diagonal? diag?
     :id id}))

