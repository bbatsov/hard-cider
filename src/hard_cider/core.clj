(ns hard-cider.core
  "A bunch of simple Clojure examples,
  showcasing various CIDER features."
  (:import
   (com.google.common.math IntMath)))

;;; basic evaluation examples
;;
;; C-x C-e
;; C-c C-p
(println "Hello, world!")
(println "Emacs is the One True Editor!")
(+ 1 2 3 4)

(range 1 20)
;; => (1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19)
(reduce + (range 1 100))

;; Java Interop
;;
;; commands like C-c C-d and M-. work for Java code as well
;; provided the Java sources are on the classpath
(.toUpperCase "bozhidar")

;; external Java library
(IntMath/gcd 400 60)

;;; Let's define some functions!
;;
;; C-M-x / C-c C-c
(defn hello-world []
  "Hello, world!!!")

(hello-world)

(defn greeting [name]
  (if (= name "Bozhidar")
    (str "Hello, Master of the Universe and Emacs!")
    (str "Hello, " name)))

(greeting "Joan")
(greeting "Bozhidar")

;;; Show image in the REPL
;;
;; Note that you can't have shell expansion characters in the URL
;; e.g. "~/kitty.jpg" won't work, as there's no shell to expand it
(clojure.java.io/file "/home/bozhidar/kitty.jpg")

;; eval up to point and eval in context
(let [x 1
      y 10]
    (+ x y)
    (* x y)
    (- y x))

;;; Tracing, debugging and profiling
(defn fact-rec [n]
  (if (= n 1)
    1
    (*' n (fact-rec (dec n)))))

(defn fact-iter [n]
  (reduce *' (range 1 (inc n))))

(fact-rec 10)
(fact-iter 10)

;; the output from those expressions in end
;; in your REPL buffer
(time (fact-rec 100))
(time (fact-iter 100))

;; tree recursion
(defn fibo-rec [n]
  (case n
    0 0
    1 1
    (+' (fibo-rec (dec n)) (fibo-rec (- n 2)))))

(defn fibo-iter
  ([n] (fibo-iter 0 1 n))
  ([curr nxt n]
   (cond
     (zero? n) curr
     :else (recur nxt (+' curr nxt) (dec n)))))

(fibo-rec 10)
(fibo-iter 10)

;; simplest profiling
;; can we do better?
(time (fibo-rec 30))
(time (fibo-iter 30))
