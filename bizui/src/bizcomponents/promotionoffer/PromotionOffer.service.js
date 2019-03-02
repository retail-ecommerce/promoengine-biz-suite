import { get,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'


const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}promotionOfferManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}promotionOfferManager/loadPromotionOffer/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidatePromotionLevel = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}promotionOfferManager/requestCandidatePromotionLevel/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPromotionLevel = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}promotionOfferManager/transferToAnotherPromotionLevel/id/anotherPromotionLevelId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidatePromotion = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}promotionOfferManager/requestCandidatePromotion/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPromotion = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}promotionOfferManager/transferToAnotherPromotion/id/anotherPromotionId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}






const PromotionOfferService = { view,
  load,
  requestCandidatePromotionLevel,
  requestCandidatePromotion,
  transferToAnotherPromotionLevel,
  transferToAnotherPromotion }
export default PromotionOfferService

