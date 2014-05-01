## 0.2.17

- Update to pallet-jclouds 1.7.3 -- resolves issue with java 6

## 0.2.16

- Update pallet-jclouds to 1.7.1 for backwards compat w/jdk1.6.

## 0.2.15

- Prepare for releases with lein-pallet-release

- Update README w/ info on options.

- Upgrade default pallet-vmfest to 0.4.0-alpha.1 (for VBox 4.3.x)

- add support for pallet-jclouds-1.7.0 and make it the default

## 0.2.14

- Update to pallet-RC.6 and pallet-vmfest 0.3.0-beta.2

## 0.2.13

- Add a pallet.clj file

- Update to use plan-fn

- Use :require :refer rather than :use :only
  Update to latest best practice for namespace declarations.

## 0.2.12

- Update to pallet 0.8.0-RC.1 as default

## 0.2.11

- Update to pallet 0.7.4 and enable 0.8.0

- Output template data when DEBUG is set

- Add README to template

## 0.2.10

- Update to produce a clojure 1.4 project

- Use a workaround for reply logging with profiles
  Remove the :repl-options workaround and use profiles.

## 0.2.9

- Update to pallet 0.7.3

## 0.2.8

- Add logging block for clj-ssh
  add the block to log clj-ssh to the compute logfile and a comment making
  it clear how to run on dubugging for ssh keys.

- s/sanitized/name/ in repl.clj. Fixes #2

## 0.2.7

- Add workaround for commons-logging in repl task
  The lein repl task adds commons-logging to our classpath, messing up the
  logging configuration. This forces use of slf4j.

## 0.2.6

- Update to latest release versions
  pallet 0.7.2 lein-pallet 0.5.2 pallet-jclouds 1.4.2

- Add test jar to lein2-style dependencies

## 0.2.5

- Update to pallet-lein 0.5.1

## 0.2.4

- Fix project.clj to use :plugins for the pallet plugin

## 0.2.3

- Update to pallet 0.7.1

## 0.2.2

- This is identical to 0.2.1, but is built with lein 1.x, to ensure that
  it is compatible with lein 1.x plugin install.

## 0.2.1

- Add pallet-lein to generated project.clj

### Known Issues

- Does not work with lein 1.x

## 0.2.0

- Update pallet version to 0.7.0

- Rename artifact to pallet/lein-template
  Lein 2 will automatically resolve this artifact name when invoked with
  'lein new pallet'

- Update default to pallet-vmfest 0.2.0-beta.3

- Update to pallet-jclouds 1.3.0-beta.1

- Reduce the default log levels for jclouds to INFO

- Add allblobstore as a jclouds dependency

- Add clojure 1.3.0 as a dependency
  the add-service task seems to dislike clojure 1.2.1

- Remove pallet 0.6.x support

## 0.1.0

Initial release
