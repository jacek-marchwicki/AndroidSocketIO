# -*- coding: utf-8 -*-
#   Copyright 2015 Jacek Marchwicki <jacek.marchwicki@gmail.com>
#
#   Licensed under the Apache License, Version 2.0 (the "License");
#   you may not use this file except in compliance with the License.
#   You may obtain a copy of the License at
#
#       http://www.apache.org/licenses/LICENSE-2.0
#
#   Unless required by applicable law or agreed to in writing, software
#   distributed under the License is distributed on an "AS IS" BASIS,
#   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#   See the License for the specific language governing permissions and
#   limitations under the License.

from setuptools import setup, find_packages
setup(
  name = "websockets-server",
  version = "0.0.1",
  author = "Jacek Marchwicki",
  author_email = "jacek.marchwicki@gmail.com",
  packages = find_packages(),
  license = "Apache 2.0",
  install_requires = ['autobahn', 'SQLAlchemy', 'python-daemon', 'service_identity'],
  entry_points = {
    "console_scripts": [
      'websockets-server = example.hub:main',
    ]
  },
  classifiers = [
    "Development Status :: 2 - Pre-Alpha",
    "Environment :: Console",
    "Programming Language :: Python",
    "Topic :: Software Development :: Build Tools",
    "License :: OSI Approved :: Apache Software License",
  ],
  url = "https://github.com/jacek-marchwicki/AndroidSocketIO",
)
