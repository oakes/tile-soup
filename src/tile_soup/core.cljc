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

(defn- parse-xml [xml]
  #?(:clj  (xml/parse (java.io.StringReader. xml))
     :cljs (xml/parse-str xml)))

(s/fdef parse
  :args (s/cat :spec (s/? qualified-keyword?) :tiled-map string?)
  :ret map?)

(defn parse
  "Parses `tiled-map` (an XML-formatted string) into a hash map. If `spec` is supplied,
  the parsing will done with that spec rather than the default :tile-soup.map/map spec."
  ([tiled-map]
   (parse ::map/map tiled-map))
  ([spec tiled-map]
   (u/parse spec (record->map (parse-xml tiled-map)))))

(def ^:private ^:const horiz-bit 31)
(def ^:private ^:const vert-bit 30)
(def ^:private ^:const diag-bit 29)
(def ^:private ^:const unknown-bit 28)

(s/fdef tile-id->map
  :args (s/cat :id integer?)
  :ret map?)

(defn tile-id->map [id]
  (let [horiz? (bit-test id horiz-bit)
        vert? (bit-test id vert-bit)
        diag? (bit-test id diag-bit)
        id (reduce bit-clear id [horiz-bit vert-bit diag-bit unknown-bit])]
    {:horizontal? horiz?
     :vertical? vert?
     :diagonal? diag?
     :id id}))

