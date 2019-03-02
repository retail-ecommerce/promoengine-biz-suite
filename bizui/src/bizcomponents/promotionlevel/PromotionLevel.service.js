import { get,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'


const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}promotionLevelManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}promotionLevelManager/loadPromotionLevel/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}promotionLevelManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}promotionLevelManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addPromotionOffer = (targetObjectId, parameters) => {
  const url = `${PREFIX}promotionLevelManager/addPromotionOffer/promotionLevelId/name/parameter/promotionId/tokensExpr/`
  const promotionLevelId = targetObjectId
  const requestParameters = { ...parameters, promotionLevelId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updatePromotionOffer = (targetObjectId, parameters) => {
  const url = `${PREFIX}promotionLevelManager/updatePromotionOfferProperties/promotionLevelId/id/name/parameter/tokensExpr/`
  const promotionLevelId = targetObjectId
  const requestParameters = { ...parameters, promotionLevelId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removePromotionOfferList = (targetObjectId, parameters) => {
  const url = `${PREFIX}promotionLevelManager/removePromotionOfferList/promotionLevelId/promotionOfferIds/tokensExpr/`
  const requestParameters = { ...parameters, promotionLevelId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}


const PromotionLevelService = { view,
  load,
  addPromotionOffer,
  updatePromotionOffer,
  removePromotionOfferList,
  requestCandidatePlatform,
  transferToAnotherPlatform }
export default PromotionLevelService

