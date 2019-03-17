(ns tile-soup.image
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]
            [tile-soup.data :as data]))

(s/def ::format string?)
(s/def ::id u/str->int)
(s/def ::source string?)
(s/def ::trans string?)
(s/def ::width u/str->int)
(s/def ::height u/str->int)

(s/def ::attrs (s/keys
                 :opt-un [::format
                          ::id
                          ::source
                          ::trans
                          ::width
                          ::height]))

(defmulti spec :tag)
(defmethod spec :data [_] ::data/data)
(defmethod spec :default [x]
  (throw (ex-info (str (:tag x) " not supported in image tags") {})))
(s/def ::content (u/conformer spec))

(s/def ::image (s/keys :req-un [::attrs ::content]))

