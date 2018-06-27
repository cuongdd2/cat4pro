/*
 *
 *  * Copyright (c) 2018 Apple Inc. All rights reserved.
 *  * No part of this application may be reproduced without Apple Inc.'s express consent.
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an 'AS IS' BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package part1

object _1Composition extends App {

  def id[A](a: A): A = a

  def compose[A, B, B2 >: B, C](f: A => B, g: B2 => C): A => C = a => g(f(a))

  def f(i: Int): String = s"[$i]"

  def fact(n: Int): Long = (1 to n) product

}
